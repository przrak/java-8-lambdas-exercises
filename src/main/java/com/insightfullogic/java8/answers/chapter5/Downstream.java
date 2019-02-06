package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter5.StringCollector;
import com.insightfullogic.java8.examples.chapter5.StringCombiner;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Downstream {
    public static void main(String[] args) {
        List<Artist> artists = SampleData.getThreeArtists();

        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1) builder.append(", ");
            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        String result = builder.toString();

        //=====>

        StringBuilder builder1 = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName).forEach(name -> {
            if (builder1.length() > 1)
                builder1.append(", ");
            builder1.append(name);
        });
        builder1.append("]");
        String result1 = builder1.toString();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);

        Optional<Integer> sum = numbers.stream()
                .reduce((left, right) -> left + right);

        sum.ifPresent(System.out::println);

        StringBuilder reduced = artists.stream()
                .map(Artist::getName)
                .reduce(new StringBuilder(), (builder2, name) -> {
                    if (builder2.length() > 0) builder2.append(", ");
                    builder2.append(name);
                    return builder2;
                }, (left, right) -> left.append(right));
        reduced.insert(0, "[");
        reduced.append("]");
        String result2 = reduced.toString();

        StringCombiner combined = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"), StringCombiner::add,
                        StringCombiner::merge);
        String result3 = combined.toString();

        String result4 = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge).toString();

        //RESULT REFACTORING
        String result5 = artists.stream()
                .map(Artist::getName)
                .collect(new StringCollector(", ", "[", "]"));
    }

    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                counting()));
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
