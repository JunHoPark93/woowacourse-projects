package techcourse.myblog.support.config.mapper;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.service.dto.response.CommentResponse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CommentResponseMapper extends PropertyMap<Comment, CommentResponse> {
    private Converter<LocalDateTime, Long> converter =
            context -> context.getSource().until(LocalDateTime.now(), ChronoUnit.MILLIS);

    @Override
    protected void configure() {
        using(converter).map(source.getCreatedDate()).setElapsedTime(null);
    }
}
