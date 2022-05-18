package com.icebear.stream.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icebear.stream.service.GameService;
import com.icebear.stream.service.TwitchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    // /game?game_name=whatever
    // /game
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public void getGame(@RequestParam(value = "game_name", required = false) String gameName,
                        // assign param to gameName
                        // required = false: gameName not necessary.
                        HttpServletResponse response) throws IOException, ServletException {

        try {
            if (gameName != null) {
                // gameName provided in request URL, search this game and return dedicated game information
                response.getWriter().print(new ObjectMapper().writeValueAsString(gameService.searchGame(gameName)));
            } else {
                // gameName isn't provided, return top x games
                response.getWriter().print(new ObjectMapper().writeValueAsString(gameService.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}
