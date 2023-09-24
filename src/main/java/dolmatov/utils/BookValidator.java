package dolmatov.utils;

import dolmatov.dao.BookDAO;
import dolmatov.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class BookValidator{
    // Не нужно проверять на уникальность ни одно из полей
}
