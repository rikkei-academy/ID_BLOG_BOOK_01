package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ra.model.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public interface CommentService extends RootService<Comment,Integer> {
    List<Comment> findChildById(int comId);
    List<Comment> findParentById(int comId);
    List<Comment> findByCommentParentId(int comId);
    List<Comment> findByCommentIdIn(ArrayList<Integer> listComId);
    Page<Comment> findByBook_BookName(String bookName, Pageable pageable);
    Page<Comment> findByUsers_UserName(String usersName, Pageable pageable);
}
