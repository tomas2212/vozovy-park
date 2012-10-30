/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.fascade;

import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andrej
 */
public interface ResercationFascade {
    
    ReservationDTO acceptReservation(Long id);    
    
}
