package techcourse.myblog.validator;

import techcourse.myblog.domain.ArticleRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleConstraintValidator implements ConstraintValidator<TitleConstraint, String> {
    private ArticleRepository articleRepository;

    public TitleConstraintValidator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        boolean isTitleExist = articleRepository.existsByTitle(value);
        if (isTitleExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("게시글 제목은 중복될 수 없습니다")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
