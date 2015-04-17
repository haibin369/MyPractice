package org.haibin369.springmvc.controller;

import org.apache.log4j.Logger;
import org.haibin369.springmvc.bean.MyForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/FormController")
public class FormController {

    private static final Logger LOGGER = Logger.getLogger(FormController.class);

    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String openForm(Model model) {
        LOGGER.debug("=========debug message===========");
        LOGGER.info("=========info message===========");
        model.addAttribute("myForm", new MyForm());
        return "myForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@RequestParam(value = "nickname") String nickName, @Valid MyForm myForm, BindingResult bindingResult, @RequestParam(value = "logo") MultipartFile logo, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error");
            return "myForm";
        }

        if (!logo.getContentType().equals("image/jpeg")) {
            bindingResult.reject("onlyJPEGSupported", "Only JPEG file supported!");
            return "myForm";
        }

        try {
            saveLogo(request, logo);
        } catch (IOException e) {
            bindingResult.reject("saveFileError", e.getMessage());
            return "myForm";
        }

        String username = myForm.getUsername();
        System.out.println("MyForm received:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + myForm.getPassword());
        return "forward:/FormController/viewResult/" + nickName;
    }

    @RequestMapping(value = "/viewResult/{nickName}")
    public String viewResult(@PathVariable String nickName, MyForm myForm, Model model) {
        model.addAttribute("nickName", nickName);
        model.addAttribute("myForm", myForm);
        return "formResult";
    }

    private void saveLogo(HttpServletRequest request, MultipartFile logoFile) throws IOException {
        String webRootPath = request.getServletContext().getRealPath("/");
        String imgDirPath = webRootPath + File.separator + "resources" + File.separator + "img";
        new File(imgDirPath).mkdirs();

        File localFile = new File(imgDirPath + File.separator + logoFile.getOriginalFilename());
        FileCopyUtils.copy(logoFile.getBytes(), localFile);
    }
}
