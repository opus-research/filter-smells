package br.ufal.ic.easy.main.model;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeSmellParser {

    private String smellId;
    private String name;
    private String projectName;
    private String codeElement;
    private String detectorName;
    private String details;
    private String commit;
    private Metrics metrics;
    private List<String> agglomeratedSmells;

    public List<String> getAgglomeratedSmells() {
        return agglomeratedSmells;
    }

    public void addAgglomeratedSmells(String smell) {
        this.agglomeratedSmells.add(smell);
    }

    public void setAgglomeratedSmells() {
        this.agglomeratedSmells = new ArrayList<>();
    }


    public CodeSmellParser(String smellId,
                     String name,
                     String projectName,
                     String codeElement,
                     String commit,
                     String detectorName,
                     String details,
                     Metrics metrics,
                           List<String> agglomeratedSmells) {
        this.smellId = smellId;
        this.name = name;
        this.projectName = projectName;
        this.codeElement = codeElement;
        this.commit = commit;
        this.detectorName = detectorName;
        this.details = details;
        this.metrics = metrics;
        this.agglomeratedSmells = agglomeratedSmells;
    }


    public String getSmellId() {
        return smellId;
    }

    public void setSmellId(String smellId) {
        this.smellId = smellId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCodeElement() {
        return codeElement;
    }

    public void setCodeElement(String codeElement) {
        this.codeElement = codeElement;
    }

    public String getDetectorName() {
        return detectorName;
    }

    public void setDetectorName(String detectorName) {
        this.detectorName = detectorName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setAgglomeratedSmells(List<String> agglomeratedSmells) {
        this.agglomeratedSmells = agglomeratedSmells;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }


}
