package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class IssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    ComparatorIssue comparator=new ComparatorIssue();

    String[] assigneesUfa={"Ибрагимова", "Балахонцев"};
    String[] assigneesMoscow={"Петров", "Сидорова"};
    String[] assigneesNovosibirsk={"Распутина", "Валина"};
    String[] assigneesStPeter={"Балахонцева", "Балахонцев", "Ибрагимова"};
    String[] labelsProjectOne={"Bug","Wontfix"};
    String[] labelsProjectTwo={"duplicate","Wontfix","enhancement","help wanted"};
    String[] labelsProjectThree={"help wanted","question"};
    String[] labelsProjectFour={"Bug"};

    Issue IbragimovaIssue = new Issue(794, "Баг программы", "Привет, нашла баг!",
            "Ибрагимова", assigneesUfa, labelsProjectOne, "Проект1", "Ветка1",
            20, true);
    Issue BalakhontsevIssue = new Issue(594, "Баг прогиГоги", "Привет, и я нашел!",
            "Балахонцев", assigneesUfa, labelsProjectOne, "Проект2", "Ветка1",
            19, true);
    Issue BalakhontsevaIssue = new Issue(28, "Агугаг", "Ниче вы не нашли!",
            "Балахонцева", assigneesStPeter, labelsProjectOne, "Проект1", "Ветка2",
            28, false);
    Issue PetrovIssue = new Issue(333, "Здравствуйте, мой ишу!", "Привет всем!",
            "Петров", assigneesMoscow, labelsProjectFour, "Проект1", "Ветка4",
            19, true);
    Issue IbragimovaHelpIssue = new Issue(795, "Помощь!", "Баг!",
            "Ибрагимова", assigneesUfa, labelsProjectTwo, "Проект2", "Ветка1",
            21, true);
    Issue BalakhontsevQuesIssue = new Issue(595, "Уху", "У меня вопрос!",
            "Балахонцев", assigneesNovosibirsk, labelsProjectThree, "Проект3", "Ветка1",
            5, false);
    Issue notFullIssue = new Issue(123, "Not Full Constructor", "Constructor Issue is not full",
            "Сидорова", 6, false);

    @Nested
    public class AllIssues {

        @BeforeEach
        void setIssues() {
            manager.add(IbragimovaIssue);
            manager.add(BalakhontsevIssue);
            manager.add(BalakhontsevaIssue);
            manager.add(PetrovIssue);
            manager.add(BalakhontsevQuesIssue);
            manager.add(IbragimovaHelpIssue);
        }

        @Test
        void shouldFindClose() {
            List<Issue> expected=new ArrayList<>();
            expected.add(BalakhontsevaIssue);
            expected.add(BalakhontsevQuesIssue);
            List<Issue> actual=manager.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpen() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(PetrovIssue);
            expected.add(IbragimovaHelpIssue);
            List<Issue> actual=manager.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveById() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(PetrovIssue);
            expected.add(BalakhontsevQuesIssue);
            expected.add(IbragimovaHelpIssue);
            manager.removeById(28);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldClearAll() {
            List<Issue> expected=new ArrayList<>();
            manager.clearAll();
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveAll() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            List<Issue> minused=new ArrayList<>();
            minused.add(PetrovIssue);
            minused.add(BalakhontsevQuesIssue);
            minused.add(IbragimovaHelpIssue);
            manager.removeAll(minused);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNew() {
            List<Issue> expected=new ArrayList<>();
            expected.add(BalakhontsevQuesIssue);
            expected.add(BalakhontsevIssue);
            expected.add(PetrovIssue);
            expected.add(IbragimovaIssue);
            expected.add(IbragimovaHelpIssue);
            expected.add(BalakhontsevaIssue);
            List<Issue> actual=manager.sortByNew(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOld() {
            List<Issue> expected=new ArrayList<>();
            expected.add(BalakhontsevaIssue);
            expected.add(IbragimovaHelpIssue);
            expected.add(IbragimovaIssue);
            expected.add(PetrovIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevQuesIssue);
            List<Issue> actual=manager.sortByOld(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            Issue IbragimovaIssueTest = new Issue(794, "Баг программы", "Привет, нашла баг!",
                    "Ибрагимова", assigneesUfa, labelsProjectOne, "Проект1", "Ветка1",
                    20, false);   //добавляем необходимый для получания Isuue
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssueTest);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            expected.add(PetrovIssue);
            expected.add(BalakhontsevQuesIssue);
            expected.add(IbragimovaHelpIssue);
            manager.closeIssue(794);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            Issue BalakhontsevaIssueTest = new Issue(28, "Агугаг", "Ниче вы не нашли!",
                    "Балахонцева", assigneesStPeter, labelsProjectOne, "Проект1", "Ветка2",
                    28, true);   //добавляем необходимый для получания Isuue
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssueTest);
            expected.add(PetrovIssue);
            expected.add(BalakhontsevQuesIssue);
            expected.add(IbragimovaHelpIssue);
            manager.openIssue(28);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByAuthor() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(IbragimovaHelpIssue);
            List<Issue> actual=manager.sortByAuthor("Ибрагимова");
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByAssignee() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            expected.add(IbragimovaHelpIssue);
            List<Issue> actual=manager.sortByAssignee("Балахонцев");
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByLabel() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            expected.add(PetrovIssue);
            List<Issue> actual=manager.sortByLabel("Bug");
            assertEquals(expected, actual);
        }
    }


    @Nested
    public class OneIssue {

        @Test
        void shouldAdd() {
            manager.add(notFullIssue);
            List<Issue> expected=new ArrayList<>();
            expected.add(notFullIssue);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SomeIssuesToSomeIsuues{
        @BeforeEach
    void setIssues() {
            manager.add(IbragimovaIssue);
            manager.add(BalakhontsevIssue);
            manager.add(BalakhontsevaIssue);
        }

        @Test
        void shouldAddAll() {
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            expected.add(PetrovIssue);
            expected.add(BalakhontsevQuesIssue);
            expected.add(IbragimovaHelpIssue);
            List<Issue> summed=new ArrayList<>();
            summed.add(PetrovIssue);
            summed.add(BalakhontsevQuesIssue);
            summed.add(IbragimovaHelpIssue);
            manager.addAll(summed);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldAddSame() {
            manager.add(IbragimovaIssue);
            List<Issue> expected=new ArrayList<>();
            expected.add(IbragimovaIssue);
            expected.add(BalakhontsevIssue);
            expected.add(BalakhontsevaIssue);
            expected.add(IbragimovaIssue);
            List<Issue> actual=manager.findAll();
            assertEquals(expected, actual);
        }
    }

}
