package com.example.raghack.model;
// import javax.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "job_postings")
public class JobPosting {
    
    public JobPosting(long jobId, int experience, String jobTitle, String jobDescription, String skills) {
        this.jobId = jobId;
        this.experience = experience;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.skills = skills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id") // Explicitly specify the database column name
    private Long jobId;

    @Column(name = "experience")
    private int experience;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "work_type")
    private String workType;

    @Column(name = "company_size")
    private String companySize;

    @Column(name = "job_posting_date")
    private Date jobPostingDate;

    @Column(name = "preference")
    private String preference;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact")
    private String contact;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "role")
    private String role;

    @Column(name = "job_portal")
    private String jobPortal;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "skills")
    private String skills;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_profile")
    private String companyProfile;

    @Column(name = "embedding", columnDefinition = "float8[]") // Adjust for PostgreSQL array of floats
    private float[] embedding;

    // Getters and Setters
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public Date getJobPostingDate() {
        return jobPostingDate;
    }

    public void setJobPostingDate(Date jobPostingDate) {
        this.jobPostingDate = jobPostingDate;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJobPortal() {
        return jobPortal;
    }

    public void setJobPortal(String jobPortal) {
        this.jobPortal = jobPortal;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }
}
