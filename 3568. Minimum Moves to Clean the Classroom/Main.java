class Solution{
    static class State{
        int r, c, energy, mask, moves;
        State(int r, int c, int energy, int mask, int moves){
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }
    public int minMoves(String[] classroom, int energy){
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = 0, sc = 0;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId){
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                char ch = classroom[i].charAt(j);
                if (ch == 'S'){
                    sr = i;
                    sc = j;
                } 
                else if (ch == 'L'){
                    litterId[i][j] = litterCount++;
                }
            }
        }
        int allCollected = (1 << litterCount) - 1;
        if (litterCount == 0){
            return 0;
        }
        int[][][] best = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                Arrays.fill(best[i][j], -1);
            }
        }
        Queue<State> queue = new ArrayDeque<>();
        best[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, energy, 0, 0));
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!queue.isEmpty()){
            State cur = queue.poll();
            if (cur.mask == allCollected){
                return cur.moves;
            }
            if (cur.energy < best[cur.r][cur.c][cur.mask]){
                continue;
            }
            for (int d = 0; d < 4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n){
                    continue;
                }
                if (classroom[nr].charAt(nc) == 'X'){
                    continue;
                }
                if (cur.energy == 0){
                    continue;
                }
                int newEnergy = cur.energy - 1;
                int newMask = cur.mask;
                if (classroom[nr].charAt(nc) == 'L'){
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }
                if (classroom[nr].charAt(nc) == 'R'){
                    newEnergy = energy;
                }
                if (newEnergy > best[nr][nc][newMask]){
                    best[nr][nc][newMask] = newEnergy;
                    queue.offer(new State(
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        cur.moves + 1
                    ));
                }
            }
        }
        return -1;
    }
}
