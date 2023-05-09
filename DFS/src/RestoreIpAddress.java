//leetcode 93

class RestoreIpAddress{

    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s == null || s.length() < 4 || s.length() > 12) return res;
        if(!containsAllNumber(s)) return res;
        dfs(s, 0, new ArrayList<>());
        return res;
    }

    private String validate(String s, int start, int end){
        int len = end - start + 1;
        if(len > 1 && s.charAt(start) == '0'){
            return "";
        }
        int res = Integer.parseInt(s.substring(start, end+1));
        if(res > 255){
            return "";
        }
        return s.substring(start, end+1);
    }

    private void dfs(String s, int index, List<String> path){
        if(index == s.length()){
            if(path.size() == 4){
                res.add(String.join(".", path));
            }
            return;
        }
        if(path.size() == 4){
            return;
        }
        for(int i = 0; i < 3; i++){
            if(index + i >= s.length()){
                break;
            }
            String temp = validate(s, index, index + i);
            if(!temp.isEmpty()){
                path.add(temp);
                dfs(s, index + i + 1, path);
                path.remove(path.size()-1);
            }

        }
    }

    private boolean containsAllNumber(String s){
        char[] sArray = s.toCharArray();
        for(int i = 0; i < sArray.length; i++){
            if(!Character.isDigit(sArray[i])){
                return false;
            }
        }
        return true;
    }

}