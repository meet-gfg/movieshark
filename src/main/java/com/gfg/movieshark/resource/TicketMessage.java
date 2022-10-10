package com.gfg.movieshark.resource;

import com.gfg.movieshark.domain.Show;
import com.gfg.movieshark.domain.ShowSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TicketMessage {

    private String  userName;
    private String  mobile;
    private String email;
    private Show show;
    private List<ShowSeat> seats;
}
