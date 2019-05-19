package com.filters.service;

import java.util.List;

public class MatchFile {
    private static final List<StoredImage> storedImages = Store.getFileStoredList();
    private static final int[] index = Store.getRangeIndex();
    /*
     * @param elements es el arreglo que contiene los elementos
     * @param findElement es el elemento a encontrar
     * @return resultado de la búsqueda binaria del elemento
     */
    public static String Search(CoordsColor findElement) {
        int size = storedImages.size();
        int distance = findElement.getDistance();
        int[] range = getRange(distance);
        int low = range[0], high = low + 1; int jump = 1;
        while (storedImages.get(high).getCoords().getDistance() < distance && high < range[1]-1) {
            jump *= 2; low = high; high = high + jump > range[1] -1 ? range[1] -1 : high + jump;
        }
        return binarySearch(findElement, low, high);
    }
    /*
     * @param elements es el arreglo que contiene los elementos
     * @param findElement es el elemento a encontrar
     * @param low inicio del intervalo de búsqueda
     * @param high final del intervalo de búsqueda
     * @return posición del elemento ó -1 si no se encontró
     */
    private static String binarySearch(CoordsColor findElement, int low, int high) {
        int distance  = findElement.getDistance();
        boolean match = false;
        while (low <= high) {
            int medium = (low + high) / 2;
            boolean dlow = storedImages.get(medium).getCoords().getDistance() < distance;
            boolean dhigh = storedImages.get(medium).getCoords().getDistance() > distance;
            if (dlow) low = medium + 1;
            else if (dhigh) high = medium - 1;
            else break;
        }
        StoredImage min = storedImages.get(low);
        for (int i = low; i <= high; i++) {
            StoredImage current = storedImages.get(i);
            if (current.getCoords().getDistance(findElement) < min.getCoords().getDistance(findElement))
                min = current;
        }
        return min.getFilename();
    }

    private static int[] getRange (int distance) {
        int low = 0, high;
        if (distance <= 50)  high = index[0];
        else if (distance <= 100) { low = index[0]; high = index[1]; }
        else if (distance <= 150) { low = index[1]; high = index[2]; }
        else if (distance <= 200) { low = index[2]; high = index[3]; }
        else if (distance <= 250) { low = index[3]; high = index[4]; }
        else if (distance <= 300) { low = index[4]; high = index[5]; }
        else if (distance <= 350) { low = index[5]; high = index[6]; }
        else if (distance <= 400) { low = index[6]; high = index[7]; }
        else { low = index[7]; high = index[8]; }
        int[] range = new int[2]; range[0] = low; range[1] = high;
        return range;
    }
}
