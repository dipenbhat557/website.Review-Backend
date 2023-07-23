package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int softwareId;
    private String title;
    @Column(length = 9999)
    private String description;
    private String location;
    private int yearFounded;
    private String language;
    @Column(length = 5000)
    private String differenceFromOthers;
    private String profileImageName;
    private String websiteLink;
    private String twitterId;
    private String linkedInId;
    private String videoName;
    private List<String> screenshots = new ArrayList<>();
    private int noOfResponses;
    private double rating;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;

    @OneToMany(mappedBy = "software", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "softwares")
    @JsonIgnore
    private List<Features> features = new ArrayList<>();
}
