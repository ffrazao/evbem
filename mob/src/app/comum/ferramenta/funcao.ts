export function iniciaCom(str1: string, str2: string): boolean {
    return str1.toLowerCase().substring(0, str2.length) == str2.toLowerCase();
}