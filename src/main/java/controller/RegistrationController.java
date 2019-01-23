package controller;

import entity.User;
import entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Autowired
    DistrictService districtService;

    @Autowired
    SkillService skillService;

    @Autowired
    UserSkillService userSkillService;

    @Autowired
    PriceService priceService;

    @PostMapping( "/signup")
    public @ResponseBody ResponseEntity registerUser(String username, String password, boolean sex, String name, String surname, int height, int weight, long birthday, byte[] file) throws Exception {
        int defaultCash = priceService.getPriceByName("Начальный баланс").getCost();
        byte[] picture = null;
        /*try {
            if (file != null) {
                picture = file.getBytes();
            } else {
               // picture = extractBytes("./pik.jpg");
            }
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading image");
        }*/
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthday);
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), surname, name, height, weight, sex, districtService.getDistrictById((int)(Math.random()*12+1)), calendar, file, statusService.getStatuseById(1), defaultCash);
        UserSkill userSkill = new UserSkill(user, skillService.getSkillById(user.getDistrict().getDistrictId()), 100);
        if ((username.equals("")) || (password.equals(""))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
        }
        User someUser = userService.getUserByNick(username);
        if (someUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        userService.createUser(user);
        userSkillService.createUserSkill(userSkill);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public  @ResponseBody ResponseEntity socialUser(String username, String password, boolean sex, int height, int weight, String birthday) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(birthday));
        user.setBirthday(calendar);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setNick(username);
        user.setSex(sex);
        user.setWeight(weight);
        user.setHeight(height);
        UserSkill userSkill = new UserSkill(user, skillService.getSkillById(user.getDistrict().getDistrictId()), 100);
        if ((username.equals("")) || (password.equals(""))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
        }
        User someUser = userService.getUserByNick(username);
        if (someUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        userService.createUser(user);
        userSkillService.createUserSkill(userSkill);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

}