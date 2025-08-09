package config;

import java.util.HashMap;
import java.util.Map;

public class TablaProbabilidadesAlimentacion {

    private static final Map<String, Map<String, Integer>> probabilidadesComida = new HashMap<>();

    static {
        Map<String, Integer> loboProbs = new HashMap<>();
        loboProbs.put("Lobo", 0);
        loboProbs.put("Boa", 0);
        loboProbs.put("Zorro", 0);
        loboProbs.put("Oso", 0);
        loboProbs.put("Aguila", 0);
        loboProbs.put("Caballo", 10);
        loboProbs.put("Ciervo", 15);
        loboProbs.put("Conejo", 60);
        loboProbs.put("Raton", 80);
        loboProbs.put("Cabra", 60);
        loboProbs.put("Oveja", 70);
        loboProbs.put("Jabali", 15);
        loboProbs.put("Bufalo", 10);
        loboProbs.put("Pato", 40);
        loboProbs.put("Oruga", 0);
        loboProbs.put("Planta", 0);
        probabilidadesComida.put("Lobo", loboProbs);

        Map<String, Integer> boaProbs = new HashMap<>();
        boaProbs.put("Lobo", 0);
        boaProbs.put("Boa", 0);
        boaProbs.put("Zorro", 15);
        boaProbs.put("Oso", 0);
        boaProbs.put("Aguila", 0);
        boaProbs.put("Caballo", 0);
        boaProbs.put("Ciervo", 0);
        boaProbs.put("Conejo", 20);
        boaProbs.put("Raton", 40);
        boaProbs.put("Cabra", 0);
        boaProbs.put("Oveja", 0);
        boaProbs.put("Jabali", 0);
        boaProbs.put("Bufalo", 0);
        boaProbs.put("Pato", 10);
        boaProbs.put("Oruga", 0);
        boaProbs.put("Planta", 0);
        probabilidadesComida.put("Boa", boaProbs);

        Map<String, Integer> zorroProbs = new HashMap<>();
        zorroProbs.put("Lobo", 0);
        zorroProbs.put("Boa", 0);
        zorroProbs.put("Zorro", 0);
        zorroProbs.put("Oso", 0);
        zorroProbs.put("Aguila", 0);
        zorroProbs.put("Caballo", 0);
        zorroProbs.put("Ciervo", 0);
        zorroProbs.put("Conejo", 70);
        zorroProbs.put("Raton", 90);
        zorroProbs.put("Cabra", 0);
        zorroProbs.put("Oveja", 0);
        zorroProbs.put("Jabali", 0);
        zorroProbs.put("Bufalo", 0);
        zorroProbs.put("Pato", 60);
        zorroProbs.put("Oruga", 40);
        zorroProbs.put("Planta", 0);
        probabilidadesComida.put("Zorro", zorroProbs);

        Map<String, Integer> osoProbs = new HashMap<>();
        osoProbs.put("Lobo", 80);
        osoProbs.put("Boa", 0);
        osoProbs.put("Zorro", 0);
        osoProbs.put("Oso", 0);
        osoProbs.put("Aguila", 0);
        osoProbs.put("Caballo", 40);
        osoProbs.put("Ciervo", 80);
        osoProbs.put("Conejo", 80);
        osoProbs.put("Raton", 90);
        osoProbs.put("Cabra", 70);
        osoProbs.put("Oveja", 70);
        osoProbs.put("Jabali", 50);
        osoProbs.put("Bufalo", 20);
        osoProbs.put("Pato", 10);
        osoProbs.put("Oruga", 0);
        osoProbs.put("Planta", 0);
        probabilidadesComida.put("Oso", osoProbs);

        Map<String, Integer> aguilaProbs = new HashMap<>();
        aguilaProbs.put("Lobo", 0);
        aguilaProbs.put("Boa", 0);
        aguilaProbs.put("Zorro", 10);
        aguilaProbs.put("Oso", 0);
        aguilaProbs.put("Aguila", 0);
        aguilaProbs.put("Caballo", 0);
        aguilaProbs.put("Ciervo", 0);
        aguilaProbs.put("Conejo", 90);
        aguilaProbs.put("Raton", 90);
        aguilaProbs.put("Cabra", 0);
        aguilaProbs.put("Oveja", 0);
        aguilaProbs.put("Jabali", 0);
        aguilaProbs.put("Bufalo", 0);
        aguilaProbs.put("Pato", 80);
        aguilaProbs.put("Oruga", 0);
        aguilaProbs.put("Planta", 0);
        probabilidadesComida.put("Aguila", aguilaProbs);

        Map<String, Integer> tigreProbs = new HashMap<>();
        tigreProbs.put("Lobo", 0);
        tigreProbs.put("Boa", 0);
        tigreProbs.put("Zorro", 0);
        tigreProbs.put("Oso", 0);
        tigreProbs.put("Aguila", 0);
        tigreProbs.put("Caballo", 90); // Presas grandes y veloces
        tigreProbs.put("Ciervo", 95); // Presas principales
        tigreProbs.put("Conejo", 0); // No son presas típicas
        tigreProbs.put("Raton", 0);
        tigreProbs.put("Cabra", 80);
        tigreProbs.put("Oveja", 80);
        tigreProbs.put("Jabali", 70);
        tigreProbs.put("Bufalo", 85); // Presa grande y peligrosa
        tigreProbs.put("Pato", 0);
        tigreProbs.put("Oruga", 0);
        tigreProbs.put("Planta", 0);
        probabilidadesComida.put("Tigre", tigreProbs);

        Map<String, Integer> caballoProbs = new HashMap<>();
        caballoProbs.put("Lobo", 0); caballoProbs.put("Boa", 0); caballoProbs.put("Zorro", 0);
        caballoProbs.put("Oso", 0); caballoProbs.put("Aguila", 0); caballoProbs.put("Caballo", 0);
        caballoProbs.put("Ciervo", 0); caballoProbs.put("Conejo", 0); caballoProbs.put("Raton", 0);
        caballoProbs.put("Cabra", 0); caballoProbs.put("Oveja", 0); caballoProbs.put("Jabali", 0);
        caballoProbs.put("Bufalo", 0); caballoProbs.put("Pato", 0); caballoProbs.put("Oruga", 0);
        caballoProbs.put("Planta", 100);
        probabilidadesComida.put("Caballo", caballoProbs);

        Map<String, Integer> ciervoProbs = new HashMap<>();
        ciervoProbs.put("Lobo", 0); ciervoProbs.put("Boa", 0); ciervoProbs.put("Zorro", 0);
        ciervoProbs.put("Oso", 0); ciervoProbs.put("Aguila", 0); ciervoProbs.put("Caballo", 0);
        ciervoProbs.put("Ciervo", 0); ciervoProbs.put("Conejo", 0); ciervoProbs.put("Raton", 0);
        ciervoProbs.put("Cabra", 0); ciervoProbs.put("Oveja", 0); ciervoProbs.put("Jabali", 0);
        ciervoProbs.put("Bufalo", 0); ciervoProbs.put("Pato", 0); ciervoProbs.put("Oruga", 0);
        ciervoProbs.put("Planta", 100);
        probabilidadesComida.put("Ciervo", ciervoProbs);

        Map<String, Integer> conejoProbs = new HashMap<>();
        conejoProbs.put("Lobo", 0); conejoProbs.put("Boa", 0); conejoProbs.put("Zorro", 0);
        conejoProbs.put("Oso", 0); conejoProbs.put("Aguila", 0); conejoProbs.put("Caballo", 0);
        conejoProbs.put("Ciervo", 0); conejoProbs.put("Conejo", 0); conejoProbs.put("Raton", 0);
        conejoProbs.put("Cabra", 0); conejoProbs.put("Oveja", 0); conejoProbs.put("Jabali", 0);
        conejoProbs.put("Bufalo", 0); conejoProbs.put("Pato", 0); conejoProbs.put("Oruga", 0);
        conejoProbs.put("Planta", 100);
        probabilidadesComida.put("Conejo", conejoProbs);

        Map<String, Integer> ratonProbs = new HashMap<>();
        ratonProbs.put("Lobo", 0); ratonProbs.put("Boa", 0); ratonProbs.put("Zorro", 0);
        ratonProbs.put("Oso", 0); ratonProbs.put("Aguila", 0); ratonProbs.put("Caballo", 0);
        ratonProbs.put("Ciervo", 0); ratonProbs.put("Conejo", 0); ratonProbs.put("Raton", 0);
        ratonProbs.put("Cabra", 0); ratonProbs.put("Oveja", 0); ratonProbs.put("Jabali", 0);
        ratonProbs.put("Bufalo", 0); ratonProbs.put("Pato", 0); ratonProbs.put("Oruga", 90);
        ratonProbs.put("Planta", 100);
        probabilidadesComida.put("Raton", ratonProbs);

        Map<String, Integer> cabraProbs = new HashMap<>();
        cabraProbs.put("Lobo", 0); cabraProbs.put("Boa", 0); cabraProbs.put("Zorro", 0);
        cabraProbs.put("Oso", 0); cabraProbs.put("Aguila", 0); cabraProbs.put("Caballo", 0);
        cabraProbs.put("Ciervo", 0); cabraProbs.put("Conejo", 0); cabraProbs.put("Raton", 0);
        cabraProbs.put("Cabra", 0); cabraProbs.put("Oveja", 0); cabraProbs.put("Jabali", 0);
        cabraProbs.put("Bufalo", 0); cabraProbs.put("Pato", 0); cabraProbs.put("Oruga", 0);
        cabraProbs.put("Planta", 100);
        probabilidadesComida.put("Cabra", cabraProbs);

        Map<String, Integer> ovejaProbs = new HashMap<>();
        ovejaProbs.put("Lobo", 0); ovejaProbs.put("Boa", 0); ovejaProbs.put("Zorro", 0);
        ovejaProbs.put("Oso", 0); ovejaProbs.put("Aguila", 0); ovejaProbs.put("Caballo", 0);
        ovejaProbs.put("Ciervo", 0); ovejaProbs.put("Conejo", 0); ovejaProbs.put("Raton", 0);
        ovejaProbs.put("Cabra", 0); ovejaProbs.put("Oveja", 0); ovejaProbs.put("Jabali", 0);
        ovejaProbs.put("Bufalo", 0); ovejaProbs.put("Pato", 0); ovejaProbs.put("Oruga", 0);
        ovejaProbs.put("Planta", 100);
        probabilidadesComida.put("Oveja", ovejaProbs);

        Map<String, Integer> jabaliProbs = new HashMap<>();
        jabaliProbs.put("Lobo", 0); jabaliProbs.put("Boa", 0); jabaliProbs.put("Zorro", 0);
        jabaliProbs.put("Oso", 0); jabaliProbs.put("Aguila", 0); jabaliProbs.put("Caballo", 0);
        jabaliProbs.put("Ciervo", 0); jabaliProbs.put("Conejo", 0);
        jabaliProbs.put("Raton", 50);
        jabaliProbs.put("Cabra", 0); jabaliProbs.put("Oveja", 0); jabaliProbs.put("Jabali", 0);
        jabaliProbs.put("Bufalo", 0);
        jabaliProbs.put("Pato", 0);
        jabaliProbs.put("Oruga", 90);
        jabaliProbs.put("Planta", 100);
        probabilidadesComida.put("Jabali", jabaliProbs);

        Map<String, Integer> bufaloProbs = new HashMap<>();
        bufaloProbs.put("Lobo", 0); bufaloProbs.put("Boa", 0); bufaloProbs.put("Zorro", 0);
        bufaloProbs.put("Oso", 0); bufaloProbs.put("Aguila", 0); bufaloProbs.put("Caballo", 0);
        bufaloProbs.put("Ciervo", 0); bufaloProbs.put("Conejo", 0); bufaloProbs.put("Raton", 0);
        bufaloProbs.put("Cabra", 0); bufaloProbs.put("Oveja", 0); bufaloProbs.put("Jabali", 0);
        bufaloProbs.put("Bufalo", 0);
        bufaloProbs.put("Pato", 0);
        bufaloProbs.put("Oruga", 0);
        bufaloProbs.put("Planta", 100);
        probabilidadesComida.put("Bufalo", bufaloProbs);

        Map<String, Integer> patoProbs = new HashMap<>();
        patoProbs.put("Lobo", 0); patoProbs.put("Boa", 0); patoProbs.put("Zorro", 0);
        patoProbs.put("Oso", 0); patoProbs.put("Aguila", 0); patoProbs.put("Caballo", 0);
        patoProbs.put("Ciervo", 0); patoProbs.put("Conejo", 0); patoProbs.put("Raton", 0);
        patoProbs.put("Cabra", 0); patoProbs.put("Oveja", 0); patoProbs.put("Jabali", 0);
        patoProbs.put("Bufalo", 0); patoProbs.put("Pato", 0);
        patoProbs.put("Oruga", 90);
        patoProbs.put("Planta", 100);
        probabilidadesComida.put("Pato", patoProbs);

        Map<String, Integer> orugaProbs = new HashMap<>();
        orugaProbs.put("Lobo", 0); orugaProbs.put("Boa", 0); orugaProbs.put("Zorro", 0);
        orugaProbs.put("Oso", 0); orugaProbs.put("Aguila", 0); orugaProbs.put("Caballo", 0);
        orugaProbs.put("Ciervo", 0); orugaProbs.put("Conejo", 0); orugaProbs.put("Raton", 0);
        orugaProbs.put("Cabra", 0); orugaProbs.put("Oveja", 0); orugaProbs.put("Jabali", 0);
        orugaProbs.put("Bufalo", 0); orugaProbs.put("Pato", 0);
        orugaProbs.put("Oruga", 0);
        orugaProbs.put("Planta", 100);
        probabilidadesComida.put("Oruga", orugaProbs);
    }

    public static int getProbabilidad(String depredadorEspecie, String presaEspecie) {
        Map<String, Integer> presasProb = probabilidadesComida.get(depredadorEspecie);
        if (presasProb != null) {
            return presasProb.getOrDefault(presaEspecie, 0);
        }
        return 0;
    }

    public static boolean existeDepredador(String especie) {
        return probabilidadesComida.containsKey(especie);
    }

    public static Map<String, Integer> getPresasDelDepredador(String depredadorEspecie) {
        return probabilidadesComida.get(depredadorEspecie);
    }
}