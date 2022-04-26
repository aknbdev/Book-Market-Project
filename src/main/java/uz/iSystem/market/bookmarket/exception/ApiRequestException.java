package uz.iSystem.market.bookmarket.exception;

public class ApiRequestException extends RuntimeException {
   public ApiRequestException(String message){
       super(message);
   }
}
