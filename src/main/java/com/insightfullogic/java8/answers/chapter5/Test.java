package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Test {
    public static void main(String[] args) {
//        List<Artist> artists = SampleData.getThreeArtists();
//        String result = artists.stream()
//                .map(Artist::getName).collect(Collectors.joining(", ", "[", "]"));
//        System.out.println(result);

//        Stream<Album> albums = SampleData.albums;
//
//        Map<Artist, List<Album>> albumsByArtist
//                = albums.collect(groupingBy(album -> album.getMainMusician()));
//
//        Map<Artist, Integer> numberOfAlbums = new HashMap<>();
//
//        for (Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
//            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
//        }
//
////        System.out.println(albumsByArtist);
//        System.out.println(numberOfAlbums);

        Stream<Album> albums1 = SampleData.albums;

        Map<Artist, Long> numberOfAlbums1
                = albums1.collect(groupingBy(album -> album.getMainMusician(), counting()));

        System.out.println(numberOfAlbums1);
    }

    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(maxBy(comparing(getCount)));
    }

    public Optional<Artist> biggestGroup1(Stream<Artist> artists) {
        return artists.max(comparing(artist -> artist.getMembers().count()));
    }

    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size()));
    }

    public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
    }

    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }

    public Map<Artist, List<String>> nameOfAlbumsDumb(Stream<Album> albums) {
        Map<Artist, List<Album>> albumsByArtist =
                albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, List<String>> nameOfAlbums = new HashMap<>();

        for (Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            nameOfAlbums.put(entry.getKey(), entry.getValue().stream()
                    .map(Album::getName)
                    .collect(toList()));
        }
        return nameOfAlbums;
    }

    public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                mapping(Album::getName, toList())));
    }
}
