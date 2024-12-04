package TD3.Interfaces;


import TD3.Hopital.HopitalFantastique;

/**
 * interface servant à définir les méthodes que doit implémenter une créature qui peut se régénérer
 */
public interface Regenerable {
    void regenerer(HopitalFantastique hopital);
}