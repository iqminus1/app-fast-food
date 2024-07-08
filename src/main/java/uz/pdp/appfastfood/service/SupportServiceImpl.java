package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Support;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.enums.SupportStatus;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SupportCrudDTO;
import uz.pdp.appfastfood.payload.SupportDTO;
import uz.pdp.appfastfood.repository.SupportRepository;
import uz.pdp.appfastfood.repository.UserRepository;

import java.util.List;

import static uz.pdp.appfastfood.utils.AppConstants.checkPermission;
import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResultDTO<List<SupportDTO>> allSMSFromUsers() {
        return ApiResultDTO.success(supportRepository.findAll().stream().map(this::toDTO).toList());
    }


    @Override
    public ApiResultDTO<List<SupportDTO>> smsByType(SupportStatus status) {
        try {
            checkPermission(PermissionEnum.SUPPORT_READ_ALL);
            return ApiResultDTO.success(supportRepository.findAllByStatus(status).stream().map(this::toDTO).toList());
        } catch (Exception e) {
            return ApiResultDTO.success(supportRepository.findAllByStatusAndFromUserIdOrToUserId(status, getUser().getId(), getUser().getId()).stream().map(this::toDTO).toList());
        }
    }

    @Override
    public ApiResultDTO<SupportDTO> sendSMS(SupportCrudDTO crudDTO) {
        if (crudDTO.getToUserId() != null) {
            checkPermission(PermissionEnum.FROM_SUPPORT_SEND_MESSAGE);
            Support support = new Support(getUser(), crudDTO.getText(), userRepository.findById(crudDTO.getToUserId()).orElseThrow(), SupportStatus.UNREAD_SMS);
            supportRepository.save(support);
            return ApiResultDTO.success(toDTO(support));
        }
        Support support = new Support(getUser(), crudDTO.getText(), null, SupportStatus.UNREAD_SMS);
        supportRepository.save(support);
        return ApiResultDTO.success(toDTO(support));
    }

    @Override
    public void deleteSMS(Integer id) {
        User user = getUser();
        Support support = supportRepository.find(id);
        if (!support.getFromUser().getId().equals(user.getId())) {
            throw new RuntimeException("this is not your sms");
        }
        supportRepository.delete(support);
    }

    private SupportDTO toDTO(Support support) {
        return new SupportDTO(
                support.getId(),
                support.getFromUser().getId(),
                support.getText(),
                support.getToUser().getId(),
                support.getStatus());
    }
}
