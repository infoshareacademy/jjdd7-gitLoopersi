package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DeserializationTeamImpl implements DeserializationTeam {
    @Override
    public List<Team> deserialize(String fileName) {
        List<Team> listOfTeams = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listOfTeams = (List<Team>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error with deserialization - IOE");
        } catch (IOException e) {
            System.out.println("Error with deserialization - Class not found");
        }
        return listOfTeams;
    }
}