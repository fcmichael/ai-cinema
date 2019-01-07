package pl.michalkruk.cinema.core.ticket;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketFacade {

    private final ModelMapper modelMapper;
    private final TicketTypeService ticketTypeService;

    public TicketFacade(ModelMapper modelMapper, TicketTypeService ticketTypeService) {
        this.modelMapper = modelMapper;
        this.ticketTypeService = ticketTypeService;
    }

    @Transactional(readOnly = true)
    public List<TicketTypeDTO> findAllTicketTypes() {
        return mapToDTO(ticketTypeService.findAll());
    }

    private List<TicketTypeDTO> mapToDTO(List<TicketType> ticketTypes) {
        return ticketTypes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TicketTypeDTO mapToDTO(TicketType ticketType) {
        return modelMapper.map(ticketType, TicketTypeDTO.class);
    }
}
