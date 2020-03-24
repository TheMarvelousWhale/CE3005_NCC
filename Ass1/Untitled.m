x = linspace(-2,2);
a = 0.01;
G = 10.^x;
y1 = G.*exp(-G);
y2 = G.*(exp(-2*G));
y3 = (G.*exp(-a*G))./(G*(1+2*a)+exp(-a*G));
y4 = (G.*exp(-G.*(1+a)).*(1+a-exp(-a*G)))./((1+a).*(1-exp(-a*G))+a*exp(-G.*(1+a)));



plot(x,y1);

hold on;
plot(x,y2);

plot(x,y3);
plot(x,y4);

ylim([-1,1])
grid on;
axis on;
hold off;

