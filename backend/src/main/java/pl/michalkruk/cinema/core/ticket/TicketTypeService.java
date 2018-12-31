package pl.michalkruk.cinema.core.ticket;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketTypeService {

    private final ModelMapper modelMapper;
    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(ModelMapper modelMapper, TicketTypeRepository ticketTypeRepository) {
        this.modelMapper = modelMapper;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    List<TicketTypeDTO> findAllTicketTypes() {
        List<TicketType> all = ticketTypeRepository.findAll();
        return all.stream()
                .map(type -> modelMapper.map(type, TicketTypeDTO.class))
                .collect(Collectors.toList());
    }
}
