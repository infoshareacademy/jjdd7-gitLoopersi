package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Team;

import java.util.List;

public interface SerializationTeam {
    void serialize(List<Team> teamList, String fileName);
}