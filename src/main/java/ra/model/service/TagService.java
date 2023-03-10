package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import ra.dto.request.TagRequest;
import ra.model.entity.Tag;
@Repository
public interface TagService extends RootService<Tag,Integer>{
    Tag mapTagRequestToTag(TagRequest request);
}
