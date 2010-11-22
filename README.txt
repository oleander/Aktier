begin OBS!
1 - Programmet klarar av många köp- och säljbud från samma person.
2 - Programmet klara av namn med whitespace i, t.ex. "Apan arnes aktiehandlarnamn hej hej", med hjälp av det reguljära uttrycket i Aktiehandel.
end OBS!

Tester som är gjorda.

Alla klasser är testade med sina egna testklasser, se respektive test*.java
Aktiehandel är testat med ett flertal ingånende komplexa argument som testar det viktigaste i vår kod.
En variant är exemplet som finns på kurshemsidan.

Alla är dokummenterat med hjälp av javadoc, se Doc-mappen.

Komplexitet
Kön är baserad på en uppdaterbar heap.
Registrering av ordrar har komplexiteten O(log n). I värsta fall måste swappas från botten till roten. Det blir maximalt log n steg (höjden på heapen).
Ändring av bud är O(log n). Detta beror på att vi direkt kan hitta element i listan med hjälp av positionsregistret. Därefter krävs endast log n steg i värsta fall för att uppdatera elementets position i listan.
Komplexiteten för att skriva ut köerna bör vara O(n) eftersom vi måste gå igenom varje element en gång. Även om man räknar med att vi måste "para ihop" buden på rätt sätt för att kunna skriva ut dem blir komplexiteten O(n) eftersom det tar O(1) tid att hämta ut de bästa buden.

