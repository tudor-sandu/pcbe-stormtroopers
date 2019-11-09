package com.bursagalactica;

public class Agent {
    private String numeAgent;

    public Agent(String numeAgent) {
        this.numeAgent = numeAgent;
    }

    public String getNumeAgent() {
        return this.numeAgent;
    }

    @Override
    public boolean equals(Object object) {
        Agent agent = (Agent) object;
        return object.getClass() == getClass() && agent.getNumeAgent().equals(this.numeAgent);
    }

    @Override
    public String toString() {
        return numeAgent;
    }
}
