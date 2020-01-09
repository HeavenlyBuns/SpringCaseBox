package feat.exception;

import feat.common.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author user
 * @title: GlobalExceptionHandler
 * @projectName my_git
 * @description: 全局异常处理器
 * @date 2019/4/29 14:43
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Throwable.class})
    @ResponseBody
    ApiResult handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
        return new ApiResult.Builder<>(status.value(), ex.getMessage()).builder();
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);

    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong(""));

    }


}
