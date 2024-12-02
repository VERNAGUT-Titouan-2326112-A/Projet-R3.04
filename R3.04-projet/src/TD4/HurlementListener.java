package TD4;

/**
 * Interface HurlementListener permettant de réagir à un hurlement émis par un lycanthrope en implémentant la méthode reagirAuHurlement.
 */
public interface HurlementListener {

    /**
     * Méthode permettant de réagir à un hurlement émis par un lycanthrope
     * @param typeHurlement Type de hurlement
     * @param emetteur Lycanthrope émetteur du hurlement
     */
    void reagirAuHurlement(String typeHurlement, Lycanthrope emetteur);
}

