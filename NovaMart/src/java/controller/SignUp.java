package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mail;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);

        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();
        final String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        if (firstName.isEmpty()) {
            responseObject.addProperty("message", "First name can not be empty");
        } else if (lastName.isEmpty()) {
            responseObject.addProperty("message", "Last Name can not be empty!");
        } else if (email.isEmpty()) {
            responseObject.addProperty("message", "Email can not be empty!");
        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "Please enter a valid email!");
        } else if (password.isEmpty()) {
            responseObject.addProperty("message", "Password can not be empty!");
        } else if (!Util.isPasswordValid(password)) {
            System.out.println("pasword awa...");
            responseObject.addProperty("message", "the paswrd");
            System.out.println("paswrd giya..");
        
        } else {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            if (!criteria.list().isEmpty()) {
                responseObject.addProperty("message", "User with this Email already Exists!");
            } else {
                User u = new User();
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setEmail(email);
                u.setPassword(password);
                //verification code
                final String verificationCode = Util.generateCode();
                u.setVerificationCode(verificationCode);
                //registered date
                u.setRegisterdAt(new Date());
                //save user
                session.save(u);
                session.beginTransaction().commit();

                //send mail
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Mail.sendMail(email, "SmartTrade - Verification", "<h1>" + verificationCode + "</h1>");
                            System.out.println("Email sent successfully to: " + email);
                        } catch (Exception e) {
                            System.out.println("Email sending failed: " + e.getMessage());
                        }
                    }
                }).start();

                System.out.println("User registration completed successfully!");
                System.out.flush(); // Output buffer flush කරනවා

                responseObject.addProperty("status", true);
                responseObject.addProperty("message", "Registration success. Please check your email for the verification code");
            }
            session.close();
        }

        String responseText = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);
    }
}
