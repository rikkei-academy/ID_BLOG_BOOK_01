package ra.model.serviceImple;
import org.springframework.beans.factory.annotation.Autowired;
import ra.model.entity.PasswordResetToken;
import ra.model.repository.PassResetRepository;
import ra.model.service.PassResetService;
public class PassResetServiceImple implements PassResetService{
    @Autowired
    private PassResetRepository passResetRepository;
    @Override
    public PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken) {
        return passResetRepository.save(passwordResetToken);
    }
    @Override
    public PasswordResetToken getLastTokenByUserId(int userId) {
        return passResetRepository.getLastTokenByUserId(userId);
    }
}
