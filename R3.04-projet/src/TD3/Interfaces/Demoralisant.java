package TD3.Interfaces;


import TD3.Hopital.HopitalFantastique;

/**
 * interface servant à définir les méthodes que doit implémenter une créature qui peut en démoraliser d'autres
 */
public interface Demoralisant {
    void demoraliser(HopitalFantastique hopital);
}