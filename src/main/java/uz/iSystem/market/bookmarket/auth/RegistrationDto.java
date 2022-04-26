package uz.iSystem.market.bookmarket.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
    Scanner scan = new Scanner(System.in);
    String text = scan.next();
    String[] num = text.split(" ");
    private String password;
    private String checkPassword;
    private String phone;
    private String email;
}
