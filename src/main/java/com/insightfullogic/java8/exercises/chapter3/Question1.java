package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Question1 {
    public static int addUp(Stream<Integer> numbers) {

        return numbers.reduce(0, (acc, x) -> acc + x);
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {

        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(Collectors.toList());
    }

    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
        return input.stream()
                .filter(album -> album.getTrackList().size() <= 3)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Artist> artists = SampleData.membersOfTheBeatles;
        Album album = SampleData.aLoveSupreme;

        System.out.println(artists.stream()
                .flatMap(artist -> artist.getMembers())
                .collect(Collectors.toList()));
//
//        System.out.println(artists.stream()
//                .map(artist -> Stream.of(artist.getName(), artist.getNationality()))
//                .collect(Collectors.toList()));
//
//        List<Integer> result = Stream.of(Arrays.asList(0,1), Arrays.asList(2, 3,4))
//                .flatMap(i -> i.stream())
//                .collect(Collectors.toList());
//        System.out.println(result);
//
//        IntSummaryStatistics trackLengthStats = album.getTracks()
//                .mapToInt(track -> track.getLength()).summaryStatistics();
//
//        System.out.printf("Max: %d, Min: %d, Avg: %f, Sum: %d \n", trackLengthStats.getMax(),
//                trackLengthStats.getMin(), trackLengthStats.getAverage(), trackLengthStats.getSum());
    }
}
