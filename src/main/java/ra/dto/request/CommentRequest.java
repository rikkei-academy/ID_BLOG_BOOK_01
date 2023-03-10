package ra.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentRequest {
    private String content;
    private int commentParentId;
    private int bookId;
    private boolean commentStatus;
    private ArrayList<Integer> listCommentId = new ArrayList<>();
    private ArrayList<Integer> listCommentIdChild = new ArrayList<>();
}
