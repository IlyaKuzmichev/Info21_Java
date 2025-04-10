package edu.school21.info21.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex, Model model) {
        log.error("Произошла ошибка: {}", ex.getMessage());
        // Можно передать дополнительную ссылку на страницу (например, на главную)
        String redirectUrl = "/"; // Это дефолтный URL на главную страницу

        // Добавляем сообщение об ошибке и ссылку для редиректа в модель
        model.addAttribute("error", "An unexpected error occurred: " + ex.getMessage());
        model.addAttribute("redirectUrl", redirectUrl);

        // Возвращаем шаблон error.html с сообщением об ошибке
        return new ModelAndView("error");
    }
}
