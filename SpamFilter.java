import java.util.List;

public class SpamFilter {
    private Trainer trainer;

    public SpamFilter(Trainer trainer) {
        this.trainer = trainer;
    }

    public boolean isSpam(Email email) {
        String content = email.getContent().toLowerCase();
        int spamScore = 0;
        int hamScore = 0;

        for (String spamExample : trainer.getSpamExamples()) {
            if (content.contains(spamExample)) {
                spamScore++;
            }
        }

        for (String hamExample : trainer.getHamExamples()) {
            if (content.contains(hamExample)) {
                hamScore++;
            }
        }

        return spamScore > hamScore;
    }
}
