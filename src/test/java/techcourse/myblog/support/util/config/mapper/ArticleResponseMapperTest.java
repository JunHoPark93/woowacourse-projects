package techcourse.myblog.support.util.config.mapper;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import techcourse.myblog.domain.Article;
import techcourse.myblog.service.dto.response.ArticleResponse;

public class ArticleResponseMapperTest {
    private ModelMapper modelMapper = new ModelMapper();



    @Test
    void article_mapping() {
        Article article = new Article("dfdf", "http://woowabros.github.io/img/2019-02-08/techcourse_poster.jpeg", "dfdF");

        ArticleResponse articleResponse = modelMapper.map(article, ArticleResponse.class);
        System.out.println(articleResponse);
    }
}
