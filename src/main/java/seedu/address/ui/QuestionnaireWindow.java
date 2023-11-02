package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Displays the Risk Assessment Questionnaire.
 * It contains questions and risk category information.
 */
public class QuestionnaireWindow extends UiPart<Stage> {
    public static final String SINGLE_NEWLINE = "\n";
    public static final String DOUBLE_NEWLINE = "\n\n";
    public static final String MARKS =
        "Marks → a: 1 pts   b: 2 pts   c: 3 pts   d: 4 pts   e: 5 pts";
    private static final Logger logger = LogsCenter.getLogger(QuestionnaireWindow.class);
    private static final String FXML = "QuestionnaireWindow.fxml";

    public static final String QUESTION_1 =
        "1) What is the main goal for your investments?" + SINGLE_NEWLINE
        + "a. Preservation of current assets" + SINGLE_NEWLINE
        + "b. Conservative generation of income" + SINGLE_NEWLINE
        + "c. Balanced portfolio between growth and income" + SINGLE_NEWLINE
        + "d. Growth of assets" + SINGLE_NEWLINE
        + "e. Aggressive growth of assets";
    public static final String QUESTION_2 =
        "2) I expect to pull money from my investments in:" + SINGLE_NEWLINE
            + "a. Less than 1 year" + SINGLE_NEWLINE
            + "b. 1-4 years" + SINGLE_NEWLINE
            + "c. 5-10 years" + SINGLE_NEWLINE
            + "d. 11-20 years" + SINGLE_NEWLINE
            + "e. Over 20 years";
    public static final String QUESTION_3 =
        "3) Select your current net worth (excluding primary residence):" + SINGLE_NEWLINE
            + "a. Under $100,000" + SINGLE_NEWLINE
            + "b. $100,000 - $500,000" + SINGLE_NEWLINE
            + "c. $500,000 - $1,000,000" + SINGLE_NEWLINE
            + "d. $1,000,000 - $3,000,000" + SINGLE_NEWLINE
            + "e. Over $3,000,000";
    public static final String QUESTION_4 =
        "4) Please indicate your current annual household income:" + SINGLE_NEWLINE
            + "a. Under $75,000" + SINGLE_NEWLINE
            + "b. $75,001 - $125,000" + SINGLE_NEWLINE
            + "c. $125,001 - $200,000" + SINGLE_NEWLINE
            + "d. $200,001 - $350,000" + SINGLE_NEWLINE
            + "e. $350,000+";
    public static final String QUESTION_5 =
        "5) What is the most complex investment you have owned, or would be comfortable owning?" + SINGLE_NEWLINE
            + "a. Money Markets/CDs" + SINGLE_NEWLINE
            + "b. Mutual Funds/ETFs" + SINGLE_NEWLINE
            + "c. Individual Stocks or Bonds" + SINGLE_NEWLINE
            + "d. Hard Assets (gold, art, etc.)" + SINGLE_NEWLINE
            + "e. Options, Futures or Unlisted Securities";
    public static final String QUESTION_6 =
        "6) Which best describes your tolerance to risk:" + SINGLE_NEWLINE
            + "a. Seek stable investments to preserve my principal" + SINGLE_NEWLINE
            + "b. Pursue modest increases in my investments, with low risk of loss" + SINGLE_NEWLINE
            + "c. Aim for investment growth, accepting moderate risk of loss" + SINGLE_NEWLINE
            + "d. Seek above-average growth in investments, accepting above average risk of loss" + SINGLE_NEWLINE
            + "e. Reach for maximum returns, accepting significant risk of loss";
    public static final String QUESTION_7 =
        "7) Imagine that an investment you own lost 30% of its value in 3 days. What would you do?" + SINGLE_NEWLINE
            + "a. I don’t know" + SINGLE_NEWLINE
            + "b. Sell all of my shares" + SINGLE_NEWLINE
            + "c. Sell a portion of my shares" + SINGLE_NEWLINE
            + "d. Do nothing" + SINGLE_NEWLINE
            + "e. Buy more shares";
    public static final String QUESTION_8 =
        "8) You make an investment, planning to hold it for 5 years. It then loses 20% in its first year. "
            + "How do you react?" + SINGLE_NEWLINE
            + "a. I would sell my investment because of my concerns" + SINGLE_NEWLINE
            + "b. I would consider selling part of my investment" + SINGLE_NEWLINE
            + "c. I would wait to see how it continues to perform" + SINGLE_NEWLINE
            + "d. I wouldn’t sell my investment, but would be concerned" + SINGLE_NEWLINE
            + "e. I would buy more of the investment because of the discount";

    public static final String QUESTIONNAIRE_MESSAGE =
        QUESTION_1 + DOUBLE_NEWLINE
        + QUESTION_2 + DOUBLE_NEWLINE
        + QUESTION_3 + DOUBLE_NEWLINE
        + QUESTION_4 + DOUBLE_NEWLINE
        + QUESTION_5 + DOUBLE_NEWLINE
        + QUESTION_6 + DOUBLE_NEWLINE
        + QUESTION_7 + DOUBLE_NEWLINE
        + QUESTION_8 + DOUBLE_NEWLINE;

    public static final String RISK_CATEGORY =
        "Risk Category" + SINGLE_NEWLINE
        + "Low: 1 - 8 pts" + SINGLE_NEWLINE
        + "Moderately Low: 9 - 16 pts" + SINGLE_NEWLINE
        + "Moderate: 17 - 24 pts" + SINGLE_NEWLINE
        + "Moderately High: 25 - 32 pts" + SINGLE_NEWLINE
        + "High: 33 - 40 pts";

    @FXML
    private Label marks;
    @FXML
    private Label questionnaireMessage;

    @FXML
    private Label riskCategory;

    /**
     * Creates a new QuestionnaireWindow.
     *
     * @param root Stage to use as the root of the QuestionnaireWindow.
     */
    public QuestionnaireWindow(Stage root) {
        super(FXML, root);
        assert marks != null : "Marks label cannot be null";
        assert questionnaireMessage != null : "Questionnaire message label cannot be null";
        assert riskCategory != null : "Risk category label cannot be null";
        marks.setText(MARKS);
        questionnaireMessage.setText(QUESTIONNAIRE_MESSAGE);
        riskCategory.setText(RISK_CATEGORY);
    }

    /**
     * Creates a new QuestionnaireWindow.
     */
    public QuestionnaireWindow() {
        this(new Stage());
    }

    /**
     * Shows the questionnaire window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing Risk Assessment Questionnaire page.");
        assert !isShowing() : "Questionnaire window is already showing";
        getRoot().show();
        getRoot().centerOnScreen();
        assert isShowing() : "Questionnaire window is not showing after show()";
    }

    /**
     * Returns true if the questionnaire window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the questionnaire window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the questionnaire window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
