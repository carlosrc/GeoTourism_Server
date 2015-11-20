package com.mmontes.model.service;

import com.mmontes.model.dao.CommentDao;
import com.mmontes.model.dao.TIPDao;
import com.mmontes.model.dao.UserAccountDao;
import com.mmontes.model.entity.Comment;
import com.mmontes.model.entity.TIP;
import com.mmontes.model.entity.UserAccount;
import com.mmontes.util.dto.CommentDto;
import com.mmontes.util.dto.DtoService;
import com.mmontes.util.exception.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("CommentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private TIPDao tipDao;

    @Autowired
    private DtoService dtoService;

    @Override
    public List<CommentDto> comment(String commentText, Long facebookUserId, Long TIPId) throws InstanceNotFoundException {
        UserAccount userAccount = userAccountDao.findByFBUserID(facebookUserId);
        TIP tip = tipDao.findById(TIPId);

        Comment comment = new Comment();
        comment.setUserAccount(userAccount);
        comment.setTip(tip);
        comment.setCommentText(commentText);
        comment.setCommentDate(Calendar.getInstance());

        commentDao.save(comment);

        tip.getComments().add(comment);

        tipDao.save(tip);

        return dtoService.ListComment2ListCommentDto(new ArrayList<>(tip.getComments()));
    }
}
