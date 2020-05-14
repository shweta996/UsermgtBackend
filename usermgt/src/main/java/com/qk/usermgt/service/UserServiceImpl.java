package com.qk.usermgt.service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.callback.ConfirmationCallback;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qk.usermgt.Exception.UserException;
import com.qk.usermgt.dto.AuthenticationDto;
import com.qk.usermgt.dto.Email;
import com.qk.usermgt.dto.LoginDto;
import com.qk.usermgt.dto.ResetPasswordDto;
import com.qk.usermgt.dto.UserDto;
import com.qk.usermgt.model.Authentication;
import com.qk.usermgt.model.LoginHistory;
import com.qk.usermgt.model.User;
import com.qk.usermgt.repository.UserRepository;
import com.qk.usermgt.response.Response;
import com.qk.usermgt.utility.CommonFiles;
import com.qk.usermgt.utility.EncryptUtil;
import com.qk.usermgt.utility.MailUtil;
import com.qk.usermgt.utility.TokenGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MailUtil mailSender;
	
	@Autowired
	private EncryptUtil encryptUtil;
	
	
	@Override
	public String login(LoginDto login) {
		Optional<User> optionalUser = userRepository.findByEmail(login.getUsername());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			String id = user.getId();
			try {
				if (encryptUtil.isPassword(login, user)) {
					if (user.isVerify()) {
						
						String token= tokenGenerator.generateToken(user.getId());
						LoginHistory loginHistory = new LoginHistory();
						loginHistory.setLoginDate(new Date());
						loginHistory.setUser(user);
						user.getLoginList().add(loginHistory);
						user.setLastLogin(new Date());
						user.setOnlineStatus(true);
						userRepository.save(user);
						return token;

					} else {
						throw new UserException("please verify your mail");
					}

				} else {
					throw new UserException("please enter correct password");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new UserException("Authentication failed");
			}

		} else {
			throw new UserException("user not found");
		}
	}

	@Override
	public String logout(String token) {
		String id = tokenGenerator.verifyToken(token);
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			user.setVerify(true);
			user.setOnlineStatus(false);
			userRepository.save(user);
			return "logged out";
		} else {
			throw new UserException("user is not verify");
		}
		
	}

	@Override
	public String forgotPassword(String email , StringBuffer requestUrl) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		User user = optionalUser.get();
		String id = user.getId();
		if (optionalUser.isPresent()) {
			try {
				String token = tokenGenerator.generateToken(id);
				System.out.println("token:" + token);
				String resetUrl = "http://localhost:4200/reset/" + token;
				Email sendEmail = new Email();
				sendEmail.setTo(user.getEmail());
				sendEmail.setSubject("password recovery mail");
				sendEmail.setBody("\n please reset your password by using following link:\n" + resetUrl);
				mailSender.send(sendEmail);
				return "reset password link send successfully";

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new UserException("Internal server error");

			}
		} else {
			throw new UserException("user is not present");
		}
	}

	@Override
	public String resetPassword(ResetPasswordDto resetPasswordDto,String token) {
		String id = tokenGenerator.verifyToken(token);
		Optional<User> optinalUser = userRepository.findById(id);
		if (optinalUser.isPresent()) {
			User user = optinalUser.get();
			if(!resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
				throw new UserException("Password and confirm password does not match");
			}
			
			user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
			userRepository.save(user);
			return "password change successfully";
		} else {
			throw new UserException("password not changed");
		}
	}

	
	private void userAbsent(String username, String email) {
		Optional<User> user = userRepository.findByUsernameAndEmail(username, email);
		
		if(user.isPresent()) {
			throw new UserException("User already exists");
		}
	}
	@Override
	public String registerUser(UserDto userDto, MultipartFile profilePic) throws IOException {
      userServiceImpl.userAbsent(userDto.getUsername(), userDto.getEmail());
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User newUser = modelMapper.map(userDto, User.class);
		
		newUser.setActivationStatus(true);
		newUser.setCreateTime(new Date());
		newUser.setModifiedTime(new Date());
		newUser.setLoginList(null);
		
		System.out.println("Profile pic bytes are "+profilePic.getBytes());
				
		byte[] bytes;
		try {
			bytes = profilePic.getBytes();
			String extension = profilePic.getContentType().replace("image/", "");
			String fileLocation = CommonFiles.IMAGE_FOLDER_PATH + userDto.getEmail() + "." + extension;
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			newUser.setProfilePicture(fileLocation);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		userRepository.save(newUser);
		return CommonFiles.REGISTER;
	}
	
	
		
	private User updateUserDetails(User user, UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMiddleName(userDto.getMiddleName());
		user.setAddress(userDto.getAddress());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setGender(userDto.getGender());
		user.setCountry(userDto.getCountry());
		user.setContact(userDto.getContact());
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		user.setUsername(userDto.getUsername());
		user.setUserRole(userDto.getUserRole());
		user.setPermissions(userDto.getPermission());		
		user.setModifiedTime(new Date());
		
		return user; 
	}

	@Override
	public String validateUser(String token) {
		String id = tokenGenerator.verifyToken(token);
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			user.setVerify(true);
			userRepository.save(user);
			return "user verified";
		} else {
			throw new UserException("user is not registered");
		}
	}
	
	private User userPresent(String id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserException(" id does not exists ");
		}
		return user.get();
	}

	@Override
	public String editUser(String id, UserDto userDto, MultipartFile profilePic) throws IOException {
        User user = userServiceImpl.userPresent(id);
		
		user = userServiceImpl.updateUserDetails(user, userDto);
		
		System.out.println("Profile pic bytes are "+profilePic.getBytes());
				
		byte[] bytes;
		try {
			bytes = profilePic.getBytes();
			String extension = profilePic.getContentType().replace("image/", "");
			String fileLocation = CommonFiles.IMAGE_FOLDER_PATH + userDto.getEmail() + "." + extension;
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			user.setProfilePicture(fileLocation);
		   } 
		catch (IOException e) {
			e.printStackTrace();
		}
		userRepository.save(user);
		return CommonFiles.UPDATE;
	}
	
	
	@Override
	public List<User> getUsers(String token) {
		String id= tokenGenerator.verifyToken(token);
		List<User> users = userRepository.findAll(); 
		return users;
	}

	@Override
	public User getUser(String id) {		
		User user = userPresent(id); 
		return user;
	}
	
	
	@Override
	public Map<String, Integer> getCountries() {
		List<User> users = userRepository.findAll();
		List<String> countries = new ArrayList<String>();
		Map<String, Integer> countryMap = new HashMap<String, Integer>();
		
		for(User user: users) {
			countries.add(user.getCountry().toLowerCase());
		}
		
		for(String country : countries) {
			if(countryMap.containsKey(country)) {
				countryMap.put(country, countryMap.get(country)+1);
				
			}
			else {
				countryMap.put(country, 1);
			}
			
		}
		
		return countryMap;
	}

	@Override
	public String getProfilePicture(String id) throws IOException {		
		User user = userServiceImpl.userPresent(id);
		
		String profilePic = "";
		String filePath = CommonFiles.IMAGE_FOLDER_PATH;
		File fileFolder = new File(filePath);
		System.out.println("Check 1");
		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {
					System.out.println("Check 2");
					String encodeBase64 = null;
					try {
						if ((filePath + file.getName()).equals(user.getProfilePicture())) {
							System.out.println("Check 3");
							String extension = FilenameUtils.getExtension(file.getName());
							FileInputStream fileInputStream = new FileInputStream(file);
							byte[] bytes = new byte[(int) file.length()];
							fileInputStream.read(bytes);
							encodeBase64 = Base64.getEncoder().encodeToString(bytes);
							profilePic = ("data:image/" + extension + ";base64," + encodeBase64);
							System.out.println("profile pic "+profilePic);
							fileInputStream.close();
							break;
						}
					} 
					catch (Exception e) {
						System.out.println("exception occured");
					}
				}
			}
		}
		System.out.println("Data received "+profilePic);
		return "image received";
	}

	@Override
	public String updateProfilePic(String id, MultipartFile file) throws IOException {
		User user = userPresent(id);
		
		byte[] bytes = file.getBytes();
		
		if(bytes.equals(null)) {
			throw new UserException(CommonFiles.IMAGE_ABSENT);
		}
		String extension = file.getContentType().replace("image/", "");
		String pathImg = "path";
		Path path = Paths.get(pathImg + user.getEmail() + "." + extension);
		Files.write(path, bytes);

		user.setProfilePicture(pathImg + user.getEmail() + "." + extension);
		userRepository.save(user);

		return CommonFiles.PICTURE_SET;
	}
	

	@Override
	public String removeProfilePicture(String id) {
		User user = userPresent(id);
		
		if(user.getProfilePicture() == null)
			throw new UserException(CommonFiles.IMAGE_ABSENT);
		
		user.setProfilePicture(null);
		userRepository.save(user);
		
		return CommonFiles.REMOVE_PIC;
	}
	

	@Override
	public List<User> getRegisteredUsers(String token) {
		String id=tokenGenerator.verifyToken(token);
		List<User> users = userRepository.findAll();
		
		users = users.stream().sorted((user1,user2) -> user2.getCreateTime()
				.compareTo(user1.getCreateTime())).collect(Collectors.toList());
		
		return users;
	}

	@Override
	public String updateAuthentication(String id, AuthenticationDto authentication) {
		User user = userServiceImpl.userPresent(id);
		
		Authentication authenticate = modelMapper.map(authentication, Authentication.class);
		user.setAuthentication(authenticate);		
		userRepository.save(user);
		
		return CommonFiles.AUTH_UPDATED;
	}

}
