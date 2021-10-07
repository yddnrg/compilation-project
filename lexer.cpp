#include <iostream>
#include <cstdio>
#include <map>
#include <cstring>
#include <string>
#include <cstdlib>

using namespace std;
map<string, string> print_map;
map<string, string>::iterator iter;

void init();
int is_in_map(char *s, int is_char);

int error = 0;

int main()
{
    init();
    char ch = getchar();
    while (ch!=EOF)
    {
        if (isdigit(ch))
        {
            int num = 0;
            while (isdigit(ch))
            {
                num *= 10;
                num += (ch - '0');
                ch = getchar();
            }
            cout << "Number(" << num << ")" << endl;
        }
        else if (isalpha(ch) || ch == '_')
        {
            char token[300];
            memset(token, 0, sizeof(char) * 300);
            while (isalpha(ch) || isdigit(ch) || ch == '_')
            {
                sprintf(token, "%s%c", token, ch);
                ch = getchar();
            }
            if (is_in_map(token, 0))
            {
                cout << iter->second << endl;
            }
            else
            {
                cout << "Ident(" << token << ")" << endl;
            }
        }
        else if (ch == '=')
        {
            ch = getchar();
            if (ch == '=')
            {
                cout << "Eq" << endl;
                ch = getchar();
            }
            else
            {
                cout << "Assign" << endl;
            }
        }
        else if (is_in_map(&ch, 1))
        {
            cout << iter->second << endl;
            ch = getchar();
        }
        else if (isspace(ch))
        {
            ch = getchar();
        }
        else
        {
            cout << "Err" << endl;
            return 0;
        }
    }
}
void init()
{
    print_map["if"]="If";
    print_map["else"]="Else";
    print_map["while"]="While";
    print_map["break"]="Break";
    print_map["continue"]="Continue";
    print_map["return"]="Return";
    print_map[";"]="Semicolon";
    print_map["("]="LPar";
    print_map[")"]="RPar";
    print_map["{"]="LBrace";
    print_map["}"]="RBrace";
    print_map["+"]="Plus";
    print_map["*"]="Mult";
    print_map["/"]="Div";
    print_map["<"]="Lt";
    print_map[">"]="Gt";

}
int is_in_map(char *s, int is_char)
{
    if (is_char)
    {
        *(s + 1) = 0;
    }
    string key = s;
    iter = print_map.find(key);
    return iter != print_map.end();
}