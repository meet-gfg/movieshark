package com.gfg.movieshark.resource;

import com.gfg.movieshark.domain.Show;
import com.gfg.movieshark.domain.ShowSeat;
import com.gfg.movieshark.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class TicketMessage {

    private String  userName;
    private String  mobile;
    private String email;
    private Show show;
    private List<ShowSeat> seats;
}
