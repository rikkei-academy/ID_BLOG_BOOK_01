package ra.model.service;

import org.springframework.stereotype.Service;
import ra.model.entity.PasswordResetToken;

public interface PassResetService {
    PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken);
    PasswordResetToken getLastTokenByUserId(int userId);
}