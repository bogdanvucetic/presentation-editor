package raf.graffito.dsw.controller;

import javax.swing.*;

public class ActionManager {
    private ExitAction exitAction;
    private Action oNamaAkcija;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.oNamaAkcija = new ONamaAkcija();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public Action getoNamaAkcija() {
        return oNamaAkcija;
    }

}
