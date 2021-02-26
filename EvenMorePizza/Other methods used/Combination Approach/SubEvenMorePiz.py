import itertools as it

def pizleft(ListP): 
    cnt=[]
    for i in ListP:
        if i.delv==False:           
            cnt.append(i)
    return cnt

def part(Num,t): 
    def pt(Num):
        Ar=[0]*(Num+1)
        k=1
        y=Num-1
        while k!=0:
            x=Ar[k-1]+1
            k-=1
            while (2*x<=y): 
                Ar[k]=x
                y-=x
                k+=1
            l=k+1
            while x<=y:
                Ar[k]=x
                Ar[l]=y
                yield Ar[:k+2]
                x+=1
                y-=1
            Ar[k]=x+y
            y=x+y-1
            yield Ar[:k+1]
    setCom=[{2},{3},{4},{2,3},{2,4},{3,4},{2,3,4}]
    res=[]
    for i in list(pt(Num)):
        if(set(i) in setCom):
            if (i.count(2)<=t[0] and i.count(3)<=t[1] and i.count(4)<=t[2]):
                res.append(i[::-1])
    return res

def delvpiz(ListP,pts,T): 
    for i in pts:
        for j in i:
                pizS(ListP,T,j)
def setmake(x):
    Ax=set()
    for ut in x:
        Ax.update(ut.Ingre)
    return [x,Ax,len(Ax)]
def pizS(Pizzas,T,S):
    bigset=[]
    Max=0
    hp=[]
    for ip in it.combinations(pizleft(Pizzas),S):
        bigset.append(setmake(ip))
    for m in bigset:
        if(Max<m[2]):
            hp=m[:]
    for ox in hp[0]:
        Pizzas[ox.id].delv=True
    tim=Teams(S)
    tim.Piz=hp[0]
    T.append(tim)
def soln(T,Fo):
    opt=[]
    Fo="D:\\Bhushan CS\\Quarantine Codes\\CoMpE pRoG pY FiLe\\HashCode\\Prac Prob\\"+Fo
    opt.append(str(len(T))+"\n")
    for i in T:
        opt.append(i.out()+"\n")
    f=open(Fo,'w')
    f.writelines(opt)
    f.close()
class P:
    def __init__(self,id,Num,Ing):
        self.id=id
        self.No=Num
        self.Ingre=Ing[:]
        self.delv=False

class Teams:
    def __init__(self,Nm):
        self.No=Nm
        self.Piz=[]
        self.full=(Nm==len(self.Piz))
    def out(self):
        op=str(self.No)
        for i in self.Piz:
            op+=" "+str(i.id)
        return op

Filenames=['a.in','b.in','c.in','d.in','e.in']
FileOut=['a.out','b.out','c.out','d.out','e.out']
for fcn in range(len(Filenames)):
    InName="D:\\Bhushan CS\\Quarantine Codes\\CoMpE pRoG pY FiLe\\HashCode\\Prac Prob\\"+Filenames[fcn]
    print(InName+" Started")
    inputF=open(InName,"r")
    InF=inputF.readlines()
    print(InName+" : Reading Complete")
    t=InF[0].split()
    N,t=int(t[0]),list(map(int,t[1:]))
    T=[]
    Pizzas=[]
    ran=InF[1:]
    cn=0
    for i in ran:
        temp=i.split()
        Pizzas.append(P(cn,int(temp[0]),temp[1:]))
        cn+=1
    print(InName+" : Process Start")
    parts = part(N,t)
    print(InName+" : Parts Done")
    delvpiz(Pizzas,parts,T)
    print(InName+" : Process Done")
    soln(T,FileOut[fcn])
    print(InName+" : Solution Printed")
    inputF.close()