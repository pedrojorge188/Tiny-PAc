package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;

public class LeaderBoard implements Serializable {

    static final long serialVersionUID = 1L;
    int top1_score, top2_score, top3_score, top4_score, top5_score;
    String top1_name, top2_name, top3_name, top4_name, top5_name;

    public LeaderBoard() {

        top1_score = 0;
        top2_score = 0;
        top3_score = 0;
        top4_score = 0;
        top5_score = 0;
        top1_name = new String("");
        top2_name = new String("");
        top3_name = new String("");
        top4_name = new String("");
        top5_name = new String("");
    }


    public boolean verifyRequirements(int points) {

        if(points > top1_score || points > top2_score || points > top3_score || points > top4_score || points > top5_score) {
            return true;
        }

        return false;

    }

    public boolean addRequirements(String name, int points) {

        if(points > top1_score){
            top5_name = top4_name;
            top5_score = top4_score;

            top4_name = top3_name;
            top4_score = top3_score;

            top3_score = top2_score;
            top3_name = top2_name;

            top2_score = top1_score;
            top2_name = top1_name;

            top1_name = name;
            top1_score = points;
            return true;
        }else if(points > top2_score){
            top5_name = top4_name;
            top5_score = top4_score;

            top4_name = top3_name;
            top4_score = top3_score;

            top3_score = top2_score;
            top3_name = top2_name;

            top2_name = name;
            top2_score = points;
            return true;
        }else if(points > top3_score){
            top5_name = top4_name;
            top5_score = top4_score;

            top4_name = top3_name;
            top4_score = top3_score;

            top3_name = name;
            top3_score = points;
            return true;
        }else if(points > top4_score){
            top5_name = top4_name;
            top5_score = top4_score;

            top4_name = name;
            top4_score = points;
            return true;
        }else if(points > top5_score){
            top5_name = name;
            top5_score = points;
            return true;
        }

        return false;

    }

    public int lead_score(int top){
        switch (top){
            case 1 -> {return getTop1_score();}
            case 2 -> {return getTop2_score();}
            case 3 -> {return getTop3_score();}
            case 4 -> {return getTop4_score();}
            case 5 -> {return getTop5_score();}
        }

        return 0;
    }

    public String lead_name(int top){
        switch (top){
            case 1 -> {return getTop1_name();}
            case 2 -> {return getTop2_name();}
            case 3 -> {return getTop3_name();}
            case 4 -> {return getTop4_name();}
            case 5 -> {return getTop5_name();}
        }

        return " ";
    }

    public int getTop1_score() {
        return top1_score;
    }

    public int getTop2_score() {
        return top2_score;
    }

    public int getTop3_score() {
        return top3_score;
    }

    public int getTop4_score() {
        return top4_score;
    }


    public int getTop5_score() {
        return top5_score;
    }

    public String getTop1_name() {
        return top1_name;
    }

    public String getTop2_name() {
        return top2_name;
    }

    public String getTop3_name() {
        return top3_name;
    }

    public String getTop4_name() {
        return top4_name;
    }

    public String getTop5_name() {
        return top5_name;
    }

}
