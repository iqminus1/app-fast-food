package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Thought;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ThoughtCrudDTO;
import uz.pdp.appfastfood.payload.ThoughtDTO;
import uz.pdp.appfastfood.repository.ThoughtRepository;

import static uz.pdp.appfastfood.utils.AppConstants.checkPermission;
import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@Service
@RequiredArgsConstructor
public class ThoughtServiceImpl implements ThoughtService {
    private final ThoughtRepository thoughtRepository;

    @Override
    public ApiResultDTO<Page<ThoughtDTO>> read(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thought> thoughts = thoughtRepository.findAll(pageable);
        Page<ThoughtDTO> thoughtDTOS = thoughts.map(this::toDTO);
        return ApiResultDTO.success(thoughtDTOS);
    }

    @Override
    public ApiResultDTO<ThoughtDTO> read(Integer id) {
        User user = getUser();
        Thought thought = thoughtRepository.find(id);
        if (!thought.getUser().equals(user)) {
            checkPermission(PermissionEnum.THOUGHT_READ);
        }
        return ApiResultDTO.success(toDTO(thought));
    }

    @Override
    public ApiResultDTO<ThoughtDTO> create(ThoughtCrudDTO crudDTO) {
        Thought thought = new Thought(getUser(), crudDTO.getType(), crudDTO.getMessage());
        thoughtRepository.save(thought);
        return ApiResultDTO.success(toDTO(thought));
    }

    @Override
    public ApiResultDTO<ThoughtDTO> update(ThoughtCrudDTO crudDTO, Integer id) {
        Thought thought = thoughtRepository.find(id);
        if (!thought.getUser().equals(getUser())) {
            throw new RuntimeException("you can`t change it");
        }
        thought.setMessage(crudDTO.getMessage());
        thought.setType(crudDTO.getType());
        thoughtRepository.save(thought);
        return ApiResultDTO.success(toDTO(thought));
    }

    @Override
    public void delete(Integer id) {
        Thought thought = thoughtRepository.find(id);
        if (!thought.getUser().equals(getUser())) {
            checkPermission(PermissionEnum.THOUGHT_DELETE);
        }
        thoughtRepository.deleteById(id);
    }

    private ThoughtDTO toDTO(Thought thought) {
        return new ThoughtDTO(thought.getId(), thought.getUser().getId(), thought.getType(), thought.getMessage());
    }
}
