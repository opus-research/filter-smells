package br.ufal.ic.easy.main;

import br.ufal.ic.easy.main.model.CodeSmell;
import br.ufal.ic.easy.main.model.CodeSmellParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SmellFilter {



    public static void main(String[] args) {

        SmellFilter filter = new SmellFilter();
        List<CodeSmell> smells = filter.getSmellsFromJson("smells-checkstyle.json");

        List<CodeSmell> lemList = filter.findLongEnviousMethod(smells);

        List<CodeSmellParser> smellParsers = filter.getSmellParserList(lemList);
        writeCodeSmelllsToJson(smellParsers, "smelly_methods_checkstyle.json");
    }


    private List<CodeSmellParser> getSmellParserList(List<CodeSmell> smells){
        List<CodeSmellParser> smellParsers = new ArrayList<>();

        for (CodeSmell smell : smells) {

            CodeSmellParser smellParser = new CodeSmellParser(smell.getSmellId(),
                    smell.getName(),
                    smell.getProjectName(),
                    smell.getCodeElement(),
                    smell.getCommit(),
                    smell.getDetectorName(),
                    smell.getDetails(),
                    smell.getMetrics(),
                    smell.getAgglomeratedSmells());

            smellParsers.add(smellParser);
        }

        return smellParsers;
    }
    private static void writeCodeSmelllsToJson(List<CodeSmellParser> lemList, String lemFileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(lemFileName), lemList);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public List<CodeSmell> getSmellsFromJson(String smellPath)
    {
        ObjectMapper mapper = new ObjectMapper();
        List<CodeSmell> smellList = new ArrayList<>();
        try {

            CodeSmell[] composites = mapper.readValue(new File(smellPath),
                    CodeSmell[].class);

            smellList = new ArrayList<CodeSmell>(Arrays.asList(composites));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return smellList;
    }



    private List<CodeSmell> findLongEnviousMethod(List<CodeSmell> smells) {

        List<CodeSmell> lemList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            CodeSmell smell = smells.get(i);
            if(smell.getName() != null && smell.getName().equals("LongMethod")){
                smell.setAgglomeratedSmells();
                smell.addAgglomeratedSmells(smell.getName());
                lemList.add(smell);

            }
        }

        for (int i = 0; i < lemList.size(); i++) {
            for (int j = 0; j < 100000; j++) {
                CodeSmell smell = smells.get(j);

                if(smell.getName() != null && smell.getCodeElement()!= null && smell.getCommit()!= null &&
                        (!smell.getName().equals("LongMethod")) &&
                        smell.getCodeElement().equals(lemList.get(i).getCodeElement()) &&
                        smell.getCommit().equals(lemList.get(i).getCommit())){

                    lemList.get(i).addAgglomeratedSmells(smell.getName());
                }
            }
        }

        return lemList;
    }

}
