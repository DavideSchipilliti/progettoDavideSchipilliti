package it.perigea.importer.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import it.perigea.importer.dto.ScheduleDTO;
import it.perigea.importer.entities.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
	
	ScheduleDTO scheduleToScheduleDTO (Schedule schedule);
	
	Schedule scheduleDTOToSchedule (ScheduleDTO scheduleDTO);
	
	@InheritConfiguration(name = "scheduleDTOToSchedule")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule partialUpdate(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
	
	List<ScheduleDTO> listScheduleToListScheduleDTO (List<Schedule> listSchedule);
	
	List<Schedule> listScheduleDTOToListSchedule (List<ScheduleDTO> listScheduleDTO);
	
	/* Il metodo partialUpdate serve per non sovrascrivere l'entità modificando gli eventuali campi popolati (sul db) in null.
	 * Potrebbe accadere nel caso in cui il DTO ricevuto dall'utente non sia completo.
	 * Devo aver già preso l'entità dal db e passarla in ingresso al metodo insieme al DTO.
	 */
}
